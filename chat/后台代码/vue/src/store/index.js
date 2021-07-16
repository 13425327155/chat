import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    root: window.localStorage.getItem('root') === null?'': window.localStorage.getItem('root')
  },
  mutations: {
    login (state, root) {
      state.root = root
      window.localStorage.setItem('root', JSON.stringify(root))
    },
    logout(state){
      state.root = null
      window.localStorage.removeItem('root')
    }
  },
  actions: {
  },
  modules: {
  }
})
