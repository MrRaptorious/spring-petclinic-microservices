'use strict';

angular.module('invoice')
    .controller('InvoiceController', ['$http', '$state', '$stateParams', '$filter', function ($http, $state, $stateParams, $filter) {
        var self = this;
        var url = "api/invoice/owners/" + ($stateParams.ownerId || 0)
        + "/pets/"+ ($stateParams.petId || 0)
        + "/visits/" + ($stateParams.visitId || 0)
        + "/invoice";

        self.dueDate = new Date();
        self.dueDate.setDate(self.dueDate.getDate() + 10);
        self.status = "OPEN";
        self.amount = 50.00;

        $http.get(url).then(function (resp) {
            self.invoice = resp.data;
        });

        self.submit = function () {
            var data = {
                dueDate: $filter('date')(self.dueDate, 'yyyy-MM-dd'),
                status: self.status,
                amount: self.amount
            };

            $http.post(url, data).then(function () {
                $state.go('ownerDetails', { ownerId: $stateParams.ownerId });
            });
        };
    }]);
