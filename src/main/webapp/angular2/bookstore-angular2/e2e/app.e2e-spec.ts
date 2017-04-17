import { BookstoreAngular2Page } from './app.po';

describe('bookstore-angular2 App', () => {
  let page: BookstoreAngular2Page;

  beforeEach(() => {
    page = new BookstoreAngular2Page();
  });

  it('should display message saying app works', () => {
    page.navigateTo();
    expect(page.getParagraphText()).toEqual('app works!');
  });
});
