import FormPage  from '../support/form.po'
import NavPage from '../support/nav.po'

describe('My First Test', () => {
  let form=new FormPage();
  let nav=new NavPage();
  it('should go to portfolio page', () => {
    form.login("teamloki@gmail.com","teamloki")
    cy.url().should('include','portfolio')
    nav.portfolio()
    //nav.getCarTableRowColumn(1, 1).should('eq','AMZN')
  });

  it('should go to trade history page', () => {
    form.login("teamloki@gmail.com","teamloki")
    cy.url().should('include','portfolio')
    nav.portfolio()
    cy.visit("/trade")
   // nav.getCarTableRowColumn(1, 1).should('eq','Amazon')
  });

  it('should go to trade page', () => {
    form.login("teamloki@gmail.com","teamloki")
    cy.url().should('include','portfolio')
    nav.portfolio()
    cy.visit("/tradeBuySell")
    nav.tradeBuySell("1")
  });

 




})


