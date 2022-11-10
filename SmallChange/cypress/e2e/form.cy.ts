import FormPage  from '../support/form.po'

describe('My First Test', () => {
  let form=new FormPage();

  it('should login if valid', () => {
    form.login("teamloki@gmail.com","teamloki")
    cy.url().should('include','portfolio')
  });

  it('should not login if in-valid', () => {
    form.login("teamloki10","123456")
    cy.url().should('not.include','portfolio')
  });

  it('should create new account', () => {
    form.singup("teams","teamloki1","123456")
    
  });


})


