// import axios from 'axios';
//
// //响应拦截器
// axios.interceptors.request.use(
//     config => {
//         if (localStorage.getItem('userToken')) {
//             config.headers.userToken = localStorage.getItem('userToken');
//         }
//
//         return config;
//     },
//     error => {
//         return Promise.reject(error);
//     });