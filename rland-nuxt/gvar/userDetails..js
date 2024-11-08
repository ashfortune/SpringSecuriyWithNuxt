const state= reactive({
    name: null,
    email: null,
    roles: [],

})
const actions = {
    login(username,email,roles){
        state.name = username;
        state.email = email;
        state.roles = roles;
    },
    logout(username,email,roles){
        state.name = null;
        state.email = null;
        state.roles = [];
    },
    isAnonymous(){
        return state.name == null;
    }
}

export default state;
export {actions};