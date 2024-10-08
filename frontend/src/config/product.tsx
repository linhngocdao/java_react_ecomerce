import {sendGet} from "./axios.tsx";
export const getProducts = () => sendGet("/products");