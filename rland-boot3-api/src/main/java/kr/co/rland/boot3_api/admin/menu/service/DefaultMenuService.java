package kr.co.rland.boot3_api.admin.menu.service;

import kr.co.rland.boot3_api.admin.menu.dto.MenuListDto;
import kr.co.rland.boot3_api.admin.menu.dto.MenuResponseDto;
import kr.co.rland.boot3_api.admin.menu.dto.MenuSearchDto;
import kr.co.rland.boot3_api.admin.menu.mapper.MenuMapper;
import kr.co.rland.boot3_api.entity.Menu;
import kr.co.rland.boot3_api.entity.MenuImage;
import kr.co.rland.boot3_api.repository.MenuImageRepository;
import kr.co.rland.boot3_api.repository.MenuRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service("adminMenuService")
public class DefaultMenuService implements MenuService {

    private MenuRepository menuRepository;
    private MenuImageRepository menuImageRepository;
    private final ModelMapper modelMapper;

    public DefaultMenuService(MenuRepository menuRepository, MenuImageRepository menuImageRepository, ModelMapper modelMapper) {
        this.menuRepository = menuRepository;
        this.menuImageRepository = menuImageRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public MenuResponseDto getList(Integer page, String korName, List<Long> categoryIds) {

        Sort sort = Sort.by(Sort.Direction.DESC, "regDate");
        Pageable pageable = PageRequest.of(page - 1, 6, sort);


//        Page<Menu> menuPage = menuRepository.findByCQuery(korName, categoryIds, pageable);
        Page<Menu> menuPage = menuRepository.findAll(korName, 3000, page, 6);
//
//        List<MenuListDto> menuListDtos = menus.stream().map(MenuMapper::mapToMenuDto).toList();
//
//        return MenuResponseDto.builder().menus(menuListDtos).build();


        List<MenuListDto> menuListDtos = menuPage.getContent()
                .stream()
                .map(menu -> {
                    MenuListDto menuListDto = modelMapper.map(menu, MenuListDto.class);
//                    menuListDto.setMemberName(menu.member.getId()); // 필드안에있는 다른 객체의 속성도 추가할수있음
//                    menuListDto.setEngName(menu.getEngName());     // 이런식으로 서로 필드가 다를때 추가하거나 해줄수있다
                    return menuListDto;
                })
//                .map(menu-> modelMapper.map(menu,MenuListDto.class))  // 아무런 추가가 필요없을땐 이렇게 단일로 사용
//                .map(MenuMapper::mapToMenuDto) // 기존에 직접 만든 Mapper클래스를 사용한방법
                .toList();


        long totalCount = menuPage.getTotalElements();
        long totalPages = menuPage.getTotalPages();
        long elementsPerPage = menuPage.getNumberOfElements();
        boolean hasNextPage = menuPage.isLast();
        boolean hasPreviousPage = menuPage.isFirst();


        page = (page == null) ? 1 : page;
        int offset = (page - 1) % 5;
        int startNum = page - offset;

        List<Long> pages = IntStream.range(startNum, startNum + 5)
                .mapToLong(i -> (long) i)
                .boxed()
                .collect(Collectors.toList());

//        let page = useRoute().query.p || 1;
//        let offset = (page - 1) % 5;
//        startNum = page - offset;
//        let nums = Array.from({length: 5}, (_, i) => i + startNum);
//        pageNums.value = nums;


//        List<MenuDto> menuDto = menuRepository.findAll().stream().map(MenuMapper::mapToMenuDto).toList();
        return MenuResponseDto.builder()
                .menus(menuListDtos)
                .totalCount(totalCount)
                .totalPages(totalPages)
                .elementsPerPage(elementsPerPage)
                .nextPage(hasNextPage)
                .previousPage(hasPreviousPage)
                .pages(pages)
                .build();
    }

    @Override
    public MenuResponseDto getList(MenuSearchDto searchDto) {
        return getList(searchDto.getPage(),searchDto.getKeyWord(),searchDto.getCategoryIds());
    }


    //POST :api/v1/admin/menus
    @Override
    public MenuListDto getById(Long id) {

//        Menu menu = menuRepository.findById(id).orElse(null);
        Menu menu = menuRepository.findById(id).orElseThrow();
//        MenuListDto menuListDto = MenuMapper.mapToMenuDto(menu);
        MenuListDto menuListDto = modelMapper.map(menu, MenuListDto.class);
        return menuListDto;

    }

    //PUT : api/v1/admin/menus/3
    //isolation level
    // - uncommitted -dirty read
    // - committed -phantom
    // -

    @Override
    public MenuListDto create(MenuListDto menuListDto) {
        Menu menu = menuRepository.save(MenuMapper.mapToMenu(menuListDto));

        List<MenuImage> menuImages = menuListDto.getImages().stream()
                .map(imageName -> {
                    MenuImage menuImage = new MenuImage();
                    menuImage.setSrc(imageName.getSrc());
                    menuImage.setMenu(menu);
                    return menuImage;
                }).collect(Collectors.toList());

        // MenuImage 엔티티 리스트를 저장
        menuImageRepository.saveAll(menuImages);

        Menu newMenu = menuRepository.findById(menu.getId()).orElseThrow();
        MenuListDto menulistDto = modelMapper.map(newMenu, MenuListDto.class);

        return menuListDto;
    }


    @Override
    @Transactional
    public MenuListDto update(MenuListDto menuListDto) {

        Menu menu = menuRepository.findById(menuListDto.getId()).orElseThrow();

        if (menuListDto.getKorName() != null) {
            menu.setKorName(menuListDto.getKorName());
        }
        if (menuListDto.getEngName() != null) {
            menu.setEngName(menuListDto.getEngName());
        }
        if (menuListDto.getPrice() != null) {
            menu.setPrice(menuListDto.getPrice());
        }
//        if(menuDto.getRegDate() != null) {
//            menu.setRegDate(menuDto.getRegDate());
//        }
        if (menuListDto.getCategoryId() != null) {
            menu.setCategoryId(menuListDto.getCategoryId());
        }
        menuRepository.save(menu);

        Menu updatedMenu = menuRepository.findById(menuListDto.getId()).orElseThrow();

        MenuListDto menulistDto = modelMapper.map(updatedMenu, MenuListDto.class);

        return menuListDto;
    }

    @Override
    public void delete(Long id) {
        menuRepository.deleteById(id);
    }

}
