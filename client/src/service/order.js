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
}

export default OrderService;
