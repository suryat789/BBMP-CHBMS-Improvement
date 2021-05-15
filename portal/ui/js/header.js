class Header extends HTMLElement {
    constructor() {
        super();
    }

    connectedCallback() {
        this.innerHTML = `
        <header>
        <!-- nav bar contents -->
            <nav class="navbar navbar-expand-lg navbar-light bg-light cs-navbar">
                <div class="container-fluid jk-nav-bar">
                    <a class="cs-primary navbar-brand cs-header jk-home-link" href="#">
                    <img src="assests/logo.png">
                        BBMP CHBMS Dashboard
                    </a>
                </div>
            </nav>
        </header> 
      `;
    }
}

customElements.define('header-component', Header);