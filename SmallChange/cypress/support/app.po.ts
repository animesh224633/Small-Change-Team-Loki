export default class AppPage {
    navigateToHomePage() {
        cy.visit("/");
    }

    checkTitle(title: string) {
        cy.get('app-header p').should('contain.text', title);
    }

}