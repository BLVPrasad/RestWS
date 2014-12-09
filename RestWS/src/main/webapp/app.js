var app = angular.module('myApp', ['ngRoute']);
app.factory("services", ['$http', function($http) {
 var serviceBase = 'http://localhost:7080/RestWS/hexaware/employees/'
 var obj = {};
 
 obj.getEmployees = function(){
 return $http.get(serviceBase + 'emplist');
 }
 
 obj.getEmployee = function(employeeID){
	 console.log("employeeID : ",employeeID);
 return $http.get(serviceBase+''+employeeID);
 }
 
 
 obj.insertEmployee = function (employee) {
	 alert("employee"+employee.empId);
	 return $http.post(serviceBase + 'insertEmployee', employee).then(function (results) {
	 return results;
	 });
	 };
 
 
 
obj.updateEmployee = function (id,employee) {
 console.log("==   updateEmployee   ====");
 return $http.post(serviceBase + 'updateEmployee', {id:id, employee:employee}).then(function (status) {
 return status.data;
 });
 };
 
obj.deleteEmployee = function (id) {
 return $http.delete(serviceBase + 'deleteEmployee?id=' + id).then(function (status) {
 return status.data;
 });
 };
 
return obj;
}]);
 
app.controller('listCtrl', function ($scope, services) {
 services.getEmployees().then(function(data){
	console.log("employees : ",data.data);
  // alert(data.data.empId);
 $scope.employees = data.data;
 });
});
 
app.controller('editCtrl', function ($scope, $rootScope, $location, $routeParams, services, employee) {
	 alert("employeeID"+$routeParams.employeeID);
	var employeeID = ($routeParams.employeeID) ? parseInt($routeParams.employeeID) : 0;

 $rootScope.title = (employeeID > 0) ? 'Edit employee' : 'Add employee';
 $scope.buttonText = (employeeID > 0) ? 'Update employee' : 'Add New employee';
 var original = employee.data;
 //alert("employee.data"+employee.data);
 original._id = employeeID;
 $scope.employee = angular.copy(original);
 $scope.employee._id = employeeID;
 
$scope.isClean = function() {
 return angular.equals(original, $scope.employee);
 }
 
$scope.deleteEmployee = function(employee) {
	alert("inside delete");
 $location.path('/');
 if(confirm("Are you sure to delete employee number: "+$scope.employee._id)==true)
 services.deleteEmployee(employee.employeeNumber);
 };
 
$scope.saveEmployee = function(employee) {
	//alert("inside save");
  $location.path('/');
 
 console.log("employeeID :",employeeID);
 
	  if (employeeID <= 0) {
		  alert("inside if");
		  services.insertEmployee(employee); 
	  } 
	  else {
		  alert("inside else");
		  services.updateEmployee(employeeID, employee);
	}
 };
});
 
app.config(['$routeProvider',
 function($routeProvider) {
 $routeProvider.
 when('/', {
 title: 'Employees',
 templateUrl: 'employees/employees.html',
 controller: 'listCtrl'
 })
 .when('/edit-employee/:employeeID', {
 title: 'Edit employees',
 templateUrl: 'employees/edit-employee.html',
 controller: 'editCtrl',
 resolve: {
 employee: function(services, $route){
 var employeeID = $route.current.params.employeeID;
 alert("emp====="+employeeID);
 return services.getEmployee(employeeID);
 }
 }
 })
 .otherwise({
 redirectTo: '/'
 });
}]);
app.run(['$location', '$rootScope', function($location, $rootScope) {
 $rootScope.$on('$routeChangeSuccess', function (event, current, previous) {
	 $rootScope.title = "";
	 //$rootScope.title = current.$$route.title;
	 
 });
}]);