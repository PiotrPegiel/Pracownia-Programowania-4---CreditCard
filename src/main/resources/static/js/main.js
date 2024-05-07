const getProducts = async () => {
  return fetch("/api/products")
    .then(response => response.json());

}

const createProductHtmlEl = (productData) => {
    const template = `
        <div>
            <h4>${productData.name}</h4>
            <img src="https://picsum.photos/id/1/200/200" height=200 />
            <div>
                <span>${productData.price}</span>
                <button data-id="${productData.id}">Add to cart</button>
            </div>
        </div>
    `;
    const htmlEl = document.createElement("li");
    htmlEl.innerHTML = template.trim();
    return htmlEl;
}

document.addEventListener("DOMContentLoaded", () => {
    const productListEl = document.querySelector('#productsList');

    getProducts()
        .then(productAsJson => productAsJson.map(createProductHtmlEl))
        .then(productsAsHtml => {
            productsAsHtml.forEach(el => productListEl.appendChild(el))
        })
});