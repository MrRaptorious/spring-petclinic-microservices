'use strict';

angular.module('invoice')
    .controller('InvoiceController', ['$http', '$state', '$stateParams', '$filter', function ($http, $state, $stateParams, $filter) {
        var self = this;
        var url = "api/invoice/visits/" + ($stateParams.visitId || 0)
        + "/invoice";

        self.dueDate = new Date();
        self.dueDate.setDate(self.dueDate.getDate() + 10);
        self.status = "OPEN";
        self.amount = 50.00;

        var getUrl="api/invoice/invoice?visitId=" + ($stateParams.visitId || 0)
        $http.get(getUrl).then(function (resp) {
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
