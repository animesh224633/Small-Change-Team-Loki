export default class NavPage {

    login(username: string,password:string) {
        cy.visit("/");
        cy.url().should('include','login')
        cy.get('[formControlName="userName"]').type(username)
        cy.get('[formControlName="password"]').type(password)
        cy.get('button').click()
    }

    portfolio(){
        cy.visit("/portfolio")
    }

    tradeBuySell(quantity:string){
        cy.get('#quantity').type(quantity)
        cy.get('#check').click()
        cy.get("#execute").click()
    }
    getCarTableRowColumn(row: number, column: number) {
        return cy.get(`tbody tr:nth-child(${row}) :nth-child(${column})`)
                 .invoke('text');
    }

}