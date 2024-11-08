export default () => {
    const id = useState('id', () => null);
    const username = useState('username', () => null);
    const email = useState('email', () => null);
    const roles = useState('roles', () => []);
    const token = useState('token', () => []);

    const isAnonymous = () => {
        return username.value === null;
    }

    const login = (loginInfo) => {
        id.value = loginInfo.id;
        username.value = loginInfo.username;
        email.value = loginInfo.email;
        roles.value = loginInfo.roles;
        token.value = loginInfo.token;

        if (!import.meta.env.SSR) {
            localStorage.setItem('id', JSON.stringify(loginInfo.id));
            localStorage.setItem('username', loginInfo.username);
            localStorage.setItem('email', loginInfo.email);
            localStorage.setItem('roles', JSON.stringify(loginInfo.roles)); //[] -> "[]"
            localStorage.setItem('token', loginInfo.token);
        }
    }

    const loadUserFromStorage = () => {

        if (!import.meta.env.SSR) {

            if (!localStorage.getItem('id')) return;

            id.value = JSON.parse(localStorage.getItem('id'));
            token.value = localStorage.getItem('token');
            username.value = localStorage.getItem('username');
            email.value = localStorage.getItem('email');
            roles.value = JSON.parse(localStorage.getItem('roles'));

        }

    }

    const logout = () => {
        id.value = null;
        username.value = null;
        email.value = null;
        roles.value = [];
        token.value = null;
    }

    const hasRole = role => roles.value.includes(role);

    return {
        id,
        username,
        email,
        roles,
        token,
        isAnonymous,
        loadUserFromStorage,
        login,
        hasRole,
        logout
    }
}