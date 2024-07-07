const getCurrentOffer = () => {
    return fetch("/api/current-offer")
        .then(response => response.json());
}

const getProducts = async () => {
    return fetch("/api/products")
        .then(response => response.json());

}

const refreshOffer = () => {
    const totalEl = document.querySelector('#offer_total');
    const itemsCountEl = document.querySelector('#offer_items-count');

    getCurrentOffer()
        .then(offer => {
            totalEl.textContent = `${offer.total} PLN`;
            itemsCountEl.textContent = `${offer.quantity} 🛒`;
            if (offer.total >= 100){
                document.getElementById("discount").style.visibility = "visible";
            }
            console.log(offer);
            console.log(offer.quantity);
        })
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

const initializeCartHandler = (productHtmlEl) => {
    const addToCartBtn = productHtmlEl.querySelector("button[data-id]");
    addToCartBtn.addEventListener("click", () => {
        const productId = event.target.getAttribute("data-id");
        addProductToCart(productId)
            .then(() => refreshOffer());
    });
    return productHtmlEl;
}

const addProductToCart = (productId) => {
    return fetch(`/api/add-product/${productId}`, {
        method: 'POST'
    });
}

const acceptOffer = (acceptOfferRequest) => {
    return fetch("/api/accept-offer", {
        method: 'POST',
        headers: {
            "Content-Type":"application/json"
        },
        body:JSON.stringify(acceptOfferRequest)
    })
        .then(response => response.json());
}

const checkoutFormEl = document.querySelector('#checkout');
checkoutFormEl.addEventListener("submit", (event) => {
    event.preventDefault();

    const acceptOfferRequest = {
        firstName: checkoutFormEl.querySelector('input[name="first_name"]').value,
        lastName: checkoutFormEl.querySelector('input[name="lastname_name"]').value,
        email: checkoutFormEl.querySelector('input[name="email"]').value,
    }

    acceptOffer(acceptOfferRequest)
        .then(reservationDetails => window.location.href = reservationDetails.paymentUrl)
});

document.addEventListener("DOMContentLoaded", () => {
    const productListEl = document.querySelector('#productsList');

    getProducts()
        .then(productAsJson => productAsJson.map(createProductHtmlEl))
        .then(productsAsHtml => productsAsHtml.map(initializeCartHandler))
        .then(productsAsHtml => {
            productsAsHtml.forEach(el => productListEl.appendChild(el))
        })

        getCurrentOffer()
            .then(offer => refreshOffer(offer));
});