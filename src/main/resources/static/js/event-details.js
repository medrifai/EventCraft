document.addEventListener('DOMContentLoaded', function () {
    const daysEl = document.getElementById('days');
    const hoursEl = document.getElementById('hours');
    const minutesEl = document.getElementById('minutes');
    const secondsEl = document.getElementById('seconds');
    const eventDateEl = document.getElementById('eventDate');

    const eventStartDate = document.getElementById('eventStartDate').dataset.date;
    const eventDate = new Date(eventStartDate);

    function formatDate(date) {
        const days = ['Sun', 'Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat'];
        const months = ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec'];
        const day = days[date.getDay()];
        const month = months[date.getMonth()];
        const dayNumber = date.getDate();
        const year = date.getFullYear();

        return `${day}, ${month} ${dayNumber}, ${year}`;
    }

    function updateCountdown() {
        const now = new Date().getTime();
        const distance = eventDate - now;

        const days = Math.floor(distance / (1000 * 60 * 60 * 24));
        const hours = Math.floor((distance % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60));
        const minutes = Math.floor((distance % (1000 * 60 * 60)) / (1000 * 60));
        const seconds = Math.floor((distance % (1000 * 60)) / 1000);

        daysEl.textContent = String(days).padStart(2, '0');
        hoursEl.textContent = String(hours).padStart(2, '0');
        minutesEl.textContent = String(minutes).padStart(2, '0');
        secondsEl.textContent = String(seconds).padStart(2, '0');

        eventDateEl.textContent = formatDate(eventDate);
    }

    setInterval(updateCountdown, 1000);
    updateCountdown();

    const quantityEl = document.getElementById('quantity');
    const decreaseBtn = document.getElementById('decrease');
    const increaseBtn = document.getElementById('increase');
    const totalEl = document.getElementById('total');
    const categorySelect = document.querySelector('select');
    let quantity = 1;

    function updateTotal() {
        const price = parseFloat(categorySelect.value);
        totalEl.textContent = `${(price * quantity).toFixed(2)} MAD`;
    }

    categorySelect.addEventListener('change', updateTotal);
    increaseBtn.addEventListener('click', () => {
        quantity++;
        quantityEl.textContent = quantity;
        updateTotal();
    });

    decreaseBtn.addEventListener('click', () => {
        if (quantity > 1) quantity--;
        quantityEl.textContent = quantity;
        updateTotal();
    });

    updateTotal();

    const mainImage = document.querySelector('.main-image img');
    const thumbnails = document.querySelectorAll('.thumbnail img');

    thumbnails.forEach(thumbnail => {
        thumbnail.addEventListener('click', function () {
            mainImage.src = this.src;
            thumbnails.forEach(thumb => thumb.parentElement.classList.remove('active'));
            this.parentElement.classList.add('active');
        });
    });
});