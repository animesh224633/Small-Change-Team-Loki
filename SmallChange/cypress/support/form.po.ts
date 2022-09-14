export default class FormPage {

    login(username: string,password:string) {
        cy.visit("/");
        cy.url().should('include','login')
        cy.get('[formControlName="userName"]').type(username)
        cy.get('[formControlName="password"]').type(password)
        cy.get('button').click()
    }

    singup(name:string,userName:string,password:string){
        cy.visit("/");
        cy.url().should('include','login')
        cy.get('a').click()
        cy.get('[formControlName="name"]').type(name)        
        cy.get('[formControlName="userName"]').type(userName)
        cy.get('[formControlName="password"]').type(password)
        cy.get('button').click()
        this.login(userName,password)
        cy.url().should('include','portfolio')

    }
}