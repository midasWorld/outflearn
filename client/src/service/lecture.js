import axios from "axios";

class Lecture {
  constructor(baseUrl) {
    this.client = axios.create({
      baseURL: baseUrl,
    });
  }

  async all() {
    const response = await this.client.get("api/v1/lectures");
    return response.data.response;
  }

  async search(query) {
    const response = await this.client.get(`api/v1/lectures/${query}`);
    return response.data.response;
  }
}

export default Lecture;
