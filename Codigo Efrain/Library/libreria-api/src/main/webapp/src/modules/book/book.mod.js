/*
The MIT License (MIT)

Copyright (c) 2015 Los Andes University

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
*/
(function (ng) {
    var mod = ng.module('bookModule', ['ngCrud', 'ui.router']);

    mod.constant('bookModel', {
        name: 'book',
        displayName: 'Book',
        url: 'books',
        fields: {
            nombre: {
                displayName: 'Nombre',
                type: 'String',
                required: true
            },
            foto: {
                displayName: 'Foto',
                type: 'String',
                required: true
            }        
        }
    });

    mod.config(['$stateProvider',
        function($sp){
            var basePath = 'src/modules/book/';
            var baseInstancePath = basePath + 'instance/';

            $sp.state('book', {
                url: '/books?page&limit',
                abstract: true,
                
                views: {
                     mainView: {
                        templateUrl: basePath + 'book.tpl.html',
                        controller: 'bookCtrl'
                    }
                },
                resolve: {
                    model: 'bookModel',
                    books: ['Restangular', 'model', '$stateParams', function (r, model, $params) {
                            return r.all(model.url).getList($params);
                        }]
                }
            });
            $sp.state('bookList', {
                url: '/list',
                parent: 'book',
                views: {
                    bookView: {
                        templateUrl: basePath + 'list/book.list.tpl.html',
                        controller: 'bookListCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            });
            $sp.state('bookInstance', {
                url: '/{bookId:int}',
                abstract: true,
                parent: 'book',
                views: {
                    bookView: {
                        template: '<div ui-view="bookInstanceView"></div>'
                    }
                },
                resolve: {
                    book: ['books', '$stateParams', function (books, $params) {
                            return books.get($params.bookId);
                        }]
                }
            });
            $sp.state('bookDetail', {
                url: '/details',
                parent: 'bookInstance',
                views: {
                    bookInstanceView: {
                        templateUrl: baseInstancePath + 'detail/book.detail.tpl.html',
                        controller: 'bookDetailCtrl'
                    }
                }
            });
	}]);
})(window.angular);
