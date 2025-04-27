'use strict';

angular.module('invoice', ['ui.router'])
    .config(['$stateProvider', function ($stateProvider) {
        $stateProvider
            .state('invoice', {
                parent: 'app',
                url: '/owners/:ownerId/pets/:petId/visits/:visitId/invoice',
                template: '<invoice></invoice>'
            })
    }]);
