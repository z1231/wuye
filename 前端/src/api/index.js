import Axios from 'axios'
const token = localStorage.getItem('token')

export let instance = Axios.create({
  baseURL: process.env.NODE_ENV === 'production' ? `http://localhost:4000/nox-smbms` : `http://localhost:4000/nox-smbms`,
  headers: { 'token': token,
    'Access-Control-Allow-Origin': '*',
    'Access-Control-Allow-Headers' : 'Content-Type, Content-Length, Authorization, Accept, X-Requested-With , yourHeaderFeild',
    'Access-Control-Allow-Methods' : 'PUT, POST, GET, DELETE, OPTIONS',
    'Content-Type':'application/x-www-form-urlencoded',
    'X-Powered-By': ' 3.2.1'},
  withCredentials:true
})
