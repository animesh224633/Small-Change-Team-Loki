import AppPage  from '../support/app.po'

describe('My First Test', () => {
  let app = new AppPage();

  it('should display welcome message', () => {
    app.navigateToHomePage();
    app.checkTitle('Login Page');
  });
})


