
(function (ng) {
    var mod = ng.module('authorModule', ['ngCrud', 'ui.router']);

    mod.config(['$stateProvider',
        function($sp){
            var basePath = 'src/modules/author/';
            var baseModulesPath = 'src/modules/';
            var baseInstancePath = basePath + 'instance/';

            $sp.state('author', {
                url: '/authors?page&limit',
                abstract: true,
                
                views: {
                     mainView: {
                        templateUrl: basePath + 'author.tpl.html',
                        controller: 'authorCtrl'
                    }
                },
                resolve: {
                    model: 'authorModel',
                    authors: ['Restangular', 'model', '$stateParams', function (r, model, $params) {
                            return r.all(model.url).getList($params);
                        }]
                }
            });
            $sp.state('authorList', {
                url: '/list',
                parent: 'author',
                views: {
                    detailView: {
                        templateUrl: basePath + 'list/author.list.tpl.html',
                        controller: 'authorListCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            });
            $sp.state('authorNew', {
                url: '/new',
                parent: 'author',
                views: {
                    detailView: {
                        templateUrl: basePath + 'new/author.new.tpl.html',
                        controller: 'authorNewCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            });
            $sp.state('authorInstance', {
                url: '/{authorId:int}',
                abstract: true,
                parent: 'author',
                views: {
                    detailView: {
                        templateUrl: baseInstancePath + 'author.instance.tpl.html'
                    }
                },
                resolve: {
                    author: ['authors', '$stateParams', function (authors, $params) {
                            return authors.get($params.authorId);
                        }],
                    books: ['authors', 'bookModel', '$stateParams', function (authors, model, $params) {
                            return authors.get($params.authorId).then(function(data){return data.books;});
                        }],
                    bookModel: 'bookModel'
                }
            });
            $sp.state('authorDetail', {
                url: '/details',
                parent: 'authorInstance',
                views: {
                    detailInstanceView: {
                        templateUrl: baseInstancePath + 'detail/author.detail.tpl.html',
                        controller: 'authorDetailCtrl'
                    },
                    listInstanceView: {
                        templateUrl: baseModulesPath + 'book/list/book.list.tpl.html',
                        controller: 'bookListCtrl',
                        controllerAs: 'bookCtrl'
                    }
                }
            });
            $sp.state('authorEdit', {
                url: '/edit',
                sticky: true,
                parent: 'authorInstance',
                views: {
                    detailInstanceView: {
                        templateUrl: baseInstancePath + 'edit/author.edit.tpl.html',
                        controller: 'authorEditCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            });
            $sp.state('authorDelete', {
                url: '/delete',
                parent: 'authorInstance',
                views: {
                    detailInstanceView: {
                        templateUrl: baseInstancePath + 'delete/author.delete.tpl.html',
                        controller: 'authorDeleteCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            });
	}]);
})(window.angular);
