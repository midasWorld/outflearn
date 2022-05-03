import axios from "axios";

class VoucherService {
  constructor(baseUrl) {
    this.client = axios.create({
      baseURL: baseUrl,
    });
  }

  async search(query) {
    const response = await this.client.get(`api/v1/vouchers/${query}`);
    return response.data.response;
  }
}

export default VoucherService;
