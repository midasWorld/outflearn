import axios from "axios";

class OrderService {
  constructor(baseUrl) {
    this.client = axios.create({
      baseURL: baseUrl,
    });
  }

  async create(request) {
    const response = await this.client.post("api/v1/orders", request);
    return response.data.response;
  }

  async all() {
    const response = await this.client.get("api/v1/orders");
    return response.data.response;
  }

  async search(request) {
    const response = await this.client.get("api/v1/orders", request);
    return response.data.response;
  }
}

export default OrderService;
