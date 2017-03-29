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
    var mod = ng.module('reviewModule', ['ngCrud', 'ui.router']);

    mod.constant('reviewModel', {
        name: 'review',
        displayName: 'Review',
        url: 'reviews',
        fields: {
            descripcion: {
                displayName: 'Descripcion',
                type: 'String',
                required: true
            },
            autor: {
                displayName: 'Autor',
                type: 'String',
                required: true
            },
            book: {
                displayName: 'Book',
                type: 'Reference',
                model: 'bookModel',
                options: [],
                required: true
            }        }
    });

    mod.config(['$stateProvider',
        function($sp){
            var basePath = 'src/modules/review/';
            var baseInstancePath = basePath + 'instance/';

            $sp.state('review', {
                url: '/reviews?page&limit',
                abstract: true,
                
                views: {
                     mainView: {
                        templateUrl: basePath + 'review.tpl.html',
                        controller: 'reviewCtrl'
                    }
                },
                resolve: {
                    references: ['$q', 'Restangular', function ($q, r) {
                            return $q.all({
                                book: r.all('books').getList()
                            });
                        }],
                    model: 'reviewModel',
                    reviews: ['Restangular', 'model', '$stateParams', function (r, model, $params) {
                            return r.all(model.url).getList($params);
                        }]
                }
            });
            $sp.state('reviewList', {
                url: '/list',
                parent: 'review',
                views: {
                    reviewView: {
                        templateUrl: basePath + 'list/review.list.tpl.html',
                        controller: 'reviewListCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            });
            $sp.state('reviewNew', {
                url: '/new',
                parent: 'review',
                views: {
                    reviewView: {
                        templateUrl: basePath + 'new/review.new.tpl.html',
                        controller: 'reviewNewCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            });
            $sp.state('reviewInstance', {
                url: '/{reviewId:int}',
                abstract: true,
                parent: 'review',
                views: {
                    reviewView: {
                        template: '<div ui-view="reviewInstanceView"></div>'
                    }
                },
                resolve: {
                    review: ['reviews', '$stateParams', function (reviews, $params) {
                            return reviews.get($params.reviewId);
                        }]
                }
            });
            $sp.state('reviewDetail', {
                url: '/details',
                parent: 'reviewInstance',
                views: {
                    reviewInstanceView: {
                        templateUrl: baseInstancePath + 'detail/review.detail.tpl.html',
                        controller: 'reviewDetailCtrl'
                    }
                }
            });
            $sp.state('reviewEdit', {
                url: '/edit',
                sticky: true,
                parent: 'reviewInstance',
                views: {
                    reviewInstanceView: {
                        templateUrl: baseInstancePath + 'edit/review.edit.tpl.html',
                        controller: 'reviewEditCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            });
            $sp.state('reviewDelete', {
                url: '/delete',
                parent: 'reviewInstance',
                views: {
                    reviewInstanceView: {
                        templateUrl: baseInstancePath + 'delete/review.delete.tpl.html',
                        controller: 'reviewDeleteCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            });
	}]);
})(window.angular);
