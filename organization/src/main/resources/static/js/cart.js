(() => {
    // Use the same saved cart on every storefront page in this browser.
    const storageKey = 'craftedthat-cart';
    const cartPanel = document.getElementById('cart-panel');
    const cartBackdrop = document.getElementById('cart-backdrop');
    const cartItems = document.getElementById('cart-items');
    const cartCount = document.getElementById('cart-count');
    const panelCartCount = document.getElementById('panel-cart-count');
    const cartEmpty = document.getElementById('cart-empty');
    const cartTotal = document.getElementById('cart-total');
    const openCartButton = document.getElementById('open-cart');
    const closeCartButton = document.getElementById('close-cart');
    const currencyFormatter = new Intl.NumberFormat('en-US', {
        style: 'currency',
        currency: 'USD'
    });

    let cart = [];

    try {
        const savedCart = JSON.parse(localStorage.getItem(storageKey) || '[]');
        if (Array.isArray(savedCart)) cart = savedCart;
    } catch {
        localStorage.removeItem(storageKey);
    }

    function saveCart() {
        localStorage.setItem(storageKey, JSON.stringify(cart));
    }

    // Rebuild the drawer from the current cart so button handlers stay in sync.
    function renderCart() {
        cartItems.replaceChildren();
        let totalCents = 0;
        let hasUnpricedItem = false;

        cart.forEach((item, index) => {
            const listItem = document.createElement('li');
            listItem.className = 'cart-item';

            const details = document.createElement('div');
            const title = document.createElement('h3');
            title.textContent = item.title;
            const description = document.createElement('p');
            description.textContent = item.description;
            const price = document.createElement('p');
            price.className = 'cart-item-price';

            const itemPrice = Number(item.price);
            if (Number.isFinite(itemPrice) && itemPrice >= 0) {
                const itemPriceCents = Math.round(itemPrice * 100);
                totalCents += itemPriceCents;
                price.textContent = currencyFormatter.format(itemPriceCents / 100);
            } else {
                hasUnpricedItem = true;
                price.textContent = 'Price unavailable';
            }

            details.append(title, description, price);

            const removeButton = document.createElement('button');
            removeButton.className = 'remove-cart-item';
            removeButton.type = 'button';
            removeButton.textContent = 'Remove';
            removeButton.setAttribute('aria-label', `Remove ${item.title} from cart`);
            removeButton.addEventListener('click', () => {
                cart.splice(index, 1);
                saveCart();
                renderCart();
            });

            listItem.append(details, removeButton);
            cartItems.append(listItem);
        });

        cartCount.textContent = cart.length;
        panelCartCount.textContent = `(${cart.length})`;
        cartEmpty.hidden = cart.length > 0;
        cartTotal.textContent = hasUnpricedItem
            ? 'Price unavailable'
            : currencyFormatter.format(totalCents / 100);
    }

    function openCart() {
        cartPanel.hidden = false;
        cartBackdrop.hidden = false;
        document.body.classList.add('cart-open');
        closeCartButton.focus();
    }

    function closeCart() {
        cartPanel.hidden = true;
        cartBackdrop.hidden = true;
        document.body.classList.remove('cart-open');
        openCartButton.focus();
    }

    // Kit cards exist only on the Kits page; the drawer controls exist on both pages.
    document.querySelectorAll('.add-item-btn').forEach((button) => {
        button.addEventListener('click', () => {
            const card = button.closest('.kit-card');
            cart.push({
                title: card.querySelector('h2, h3').textContent.trim(),
                description: card.querySelector('.card-text').textContent.trim(),
                price: card.dataset.price
            });
            saveCart();
            renderCart();
            openCart();
        });
    });

    openCartButton.addEventListener('click', openCart);
    closeCartButton.addEventListener('click', closeCart);
    cartBackdrop.addEventListener('click', closeCart);
    document.addEventListener('keydown', (event) => {
        if (event.key === 'Escape' && !cartPanel.hidden) closeCart();
    });
    window.addEventListener('storage', (event) => {
        if (event.key === storageKey) {
            try {
                cart = JSON.parse(event.newValue || '[]');
                if (!Array.isArray(cart)) cart = [];
            } catch {
                cart = [];
            }
            renderCart();
        }
    });

    renderCart();
})();