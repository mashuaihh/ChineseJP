function formController($scope){
	
	$scope.samePswd=false;
	/*	监视密码2的输入，如果输入和密码1的相同，则可以注册。
	若不同，或者两个密码都为空，则不可注册。*/
	$scope.$watch("person.pswd2", function(newVal,oldVal,scope){
		if (newVal === oldVal){
		}
		else if(!$scope.person.pswd2){
			$scope.samePswd=false;
		}
		else if($scope.person.pswd1 !== $scope.person.pswd2){
			$scope.samePswd=false;
		}
		else if($scope.person.pswd1 === $scope.person.pswd2){
			$scope.samePswd=true;
		}
	});
/*	按监视密码2的方法监视密码1*/
	$scope.$watch("person.pswd1", function(newVal,oldVal,scope){
		if (newVal === oldVal){
		}
		else if(!$scope.person.pswd1){
			$scope.samePswd=false;
		}
		else if($scope.person.pswd1 !== $scope.person.pswd2){
			$scope.samePswd=false;
		}
		else if($scope.person.pswd1 === $scope.person.pswd2){
			$scope.samePswd=true;
		}
	});

	$scope.personList=[];

	$scope.register=function(){
		$scope.personList.push({
       	 	name : $scope.person.name,
       	 	email : $scope.person.email
	 });
		alert("注册成功");
/*		清空输入项的数据*/
		$scope.person.name="";
		$scope.person.email="";
		$scope.person.pswd1="";
		$scope.person.pswd2="";
		$scope.person.check=false;
	}

	$scope.removePerson = function (index) {
            $scope.personList.splice(index, 1);
        };


}