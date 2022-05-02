class LectureFetch {
  constructor(baseUrl) {
    this.baseUrl = baseUrl;
    this.getRequestOptions = {
      method: "GET",
      redirect: "follow",
    };
  }

  async all() {
    const response = await fetch(
      `${this.baseUrl}/api/v1/lectures`,
      this.getRequestOptions
    );
    const result_1 = await response.json();
    return result_1.response;
  }

  async search(query) {
    const response = await fetch(
      `${this.baseUrl}/api/v1/lectures/${query}`,
      this.getRequestOptions
    );
    const result_1 = await response.json();
    return result_1.response;
  }
}

export default LectureFetch;
