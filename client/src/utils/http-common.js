import axios from 'axios'

export const AXIOS_API = axios.create({
    baseURL: `/api`
})

export const AXIOS_PERSON = axios.create({
    baseURL: `/person`
})