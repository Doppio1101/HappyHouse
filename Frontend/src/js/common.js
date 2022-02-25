import axios from "axios";

const localhost = axios.create({
  baseURL: "http://localhost:9090/happyhouse",
  headers: {
    "Content-type": "application/json",
  },
});

export { localhost };
