$(document).ready(function(){
	var arr = ($(location).attr('href')).split('/');
	var len = arr.length;
	var arr2 = (arr[len-1]).split('.');
	
	//메뉴바
	if(arr2[0] == "index"){
		$('#nav > ul > li').removeClass('current');
		$('#nav > ul > li:nth-child(1)').toggleClass('current');
	}else if(arr2[0] == "news"){
		$('#nav > ul > li').removeClass('current');
		$('#nav > ul > li:nth-child(2)').toggleClass('current');
	}else if(arr2[0] == "mypage"){
		$('#nav > ul > li').removeClass('current');
		$('#nav > ul > li:nth-child(5)').toggleClass('current');
	}else if(arr2[0] == "loanCalculator"){
		$('#nav > ul > li').removeClass('current');
		$('#nav > ul > li:nth-child(4)').toggleClass('current');
	}
	$('#nav > ul > li > a').click(function(){
		$('#nav > ul > li').removeClass('current');
		$(this).parent().toggleClass('current');
	});
	
	//회원가입 : 1단계
	$('#signUp1Btn').click(function(){
		if($('input:checkbox[id="agreement_cb1"]').is(":checked") && $('input:checkbox[id="agreement_cb2"]').is(":checked")){
			location.href="/register2"
		}else{
			Swal.fire({
				  title: "약관에 동의해주세요.",
				  type: 'warning',
				  confirmButtonColor: '#3085d6',
				  cancelButtonColor: '#d33',
				  confirmButtonText: 'Yes!'
				})
		}
	});
	//회원가입 : 3단계
	$('#signUp3Btn').click(function(){
		var id = $('#signUp3Id');
		var pw = $('#signUp3Pw');
		var pwch = $('#signUp3Pwch');
		var name = $('#signUp3Name');
		var birth = $('#signUp3Birth');
		var addr = $('#signUp3Addr');
		var email = $('#signUp3Email');
		var phone = $('#signUp3Phone');
		if(id.val() == ""){
			id.focus();
		}else if(pw.val() == ""){
			pw.focus();
		}else if(pwch.val() == ""){
			pwch.focus();
		}else if(name.val() == ""){
			name.focus();
		}else if(birth.val() == ""){
			birth.focus();
		}else if(addr.val() == ""){
			addr.focus();
		}else if(email.val() == ""){
			email.focus();
		}else if(phone.val() == ""){
			phone.focus();
		}else{
			fm.submit();
		}
	});
	
	//대출계산기 : 작동
	$('.price').click(function(){
	  var clicked_id=$(this).attr('id');
	  var clicked_id_val = $('#loanPrice').val();
	  var input_val = Number(clicked_id_val)+Number(clicked_id);
	  $('#loanPrice').val(input_val);
	});
	$('.rate').click(function(){
	  var clicked_id=$(this).attr('id');
	  var clicked_id_val = $("#loanRate").val();
	  var input_val = Number(clicked_id_val)+Number(clicked_id);
	  $('#loanRate').val(input_val);
	});
	$('.period').click(function(){
	  var clicked_id=$(this).attr('id');
	  var clicked_id_val = $("#loanPeriod").val();
	  var input_val = Number(clicked_id_val)+Number(clicked_id);
	  $('#loanPeriod').val(input_val);
	});
	$('.holding').click(function(){
	  var clicked_id=$(this).attr('id');
	  var clicked_id_val = $("#holdingPeriod").val();
	  var input_val = Number(clicked_id_val)+Number(clicked_id);
	  $('#holdingPeriod').val(input_val);
	});
	$('#resetBtn').click(function(){
		$('#loanPrice').val('');
		$('#loanRate').val('');
		$('#loanPeriod').val('');
		$('#holdingPeriod').val('');
	});
	//대출계산기 : 계산버튼 클릭시
	$('#calBtn').click(function(){
		if($('#loanPrice').val() == '' || $('#loanRate').val() == '' || $('#loanPeriod').val() == ''){
			alert('정보를 모두 입력해 주세요.');
		}else if( eval($('#loanPeriod').val() - $('#holdingPeriod').val()) <= 0){
			alert( $('#loanPeriod').val()+',' +$('#holdingPeriod').val() +'거치기간은 대출기간과 같거나 클 수 없습니다.');
		}else{
			var allData = {loanPrice:$('#loanPrice').val() 
							  ,loanPeriod:$('#loanPeriod').val()
							  ,loanRate:$('#loanRate').val()
							  ,holdingPeriod :$('#holdingPeriod').val()};
			$.ajax({
				url:"/loan/result",
				method:"put",
				data: JSON.stringify(allData),
				dataType:"json",
				contentType: "application/json; charset=utf-8",
				success: function(data){
					var str2="<tr>";
					data.loan.forEach(function(loan,inx) {
						loanPriceInput.innerHTML=loan.loanPrice + "원";
						loanPeriodInput.innerHTML=loan.loanPeriod + "개월";
						loanRateInput.innerHTML=loan.loanRate +"%";
						holdingPeriodInput.innerHTML=loan.holdingPeriod + "개월";
						var str = "<tr style='background:#008485;color:white;'><td>no</td><td>상환금</td><td>납입원금</td><td>이자</td><td>납입원금계</td><td>잔금</td></tr>";
						
						for(var i =1;i<=loan.loanPeriod;i++){
							str += "<tr><td>" + i + "</td>"
							+"<td>" + addComma(loan.monthlyPayment[i])+"</td>"
							+"<td>" + addComma(loan.payment[i])+"</td>"
							+"<td>" + addComma(loan.interest[i])+"</td>"
							+"<td>" + addComma(loan.accumulatePayment[i])+"</td>"
							+"<td>" + addComma(loan.theBalance[i])+"</td></tr>"
						}
						$("#loanMethod"+inx).empty();
						$("#loanMethod"+inx).append(str);
						
						//그래프 아래에 총이자 넣기
						str2 += "<td>총이자 : "+addComma(loan.totalInterest)+"원</td>" 
					});
					str2 +="</tr>";
					$("#loangraphBottom").empty();
					$("#loangraphBottom").append(str2);
					
				},
				error:function(error){
					alert("error : " + error);
				}
			})
		}
	});
	
	//대출계산기 방법3가지
	$(".loanMethodBtn").click(function(){
		var current_id = $(this).attr("id");
		if(current_id == 'loanMethodBtn1'){
			$('#loanMethod1').hide();
			$('#loanMethod2').show();
			$('#loanMethod0').hide();
		}else if(current_id == 'loanMethodBtn2'){
			$('#loanMethod0').hide();
			$('#loanMethod2').hide();
			$('#loanMethod1').show();
		}else if(current_id == 'loanMethodBtn3'){
			$('#loanMethod1').hide();
			$('#loanMethod0').show();
			$('#loanMethod2').hide();
		}
	});
	
	//대출계산기 자세히 보기 버튼 클릭시 modal창 오픈
	var modal2 = document.getElementById('myModal');
	var modal = $("#myModal");
	var btn = $("#myBtn");
	var span = $('.closeModal');
	btn.click(function(){
		modal.show();
	});
	span.click(function(){
		modal.hide();
	});
	window.onclick = function(event) {
	    if (event.target == modal2) {
	        modal.hide();
	    }
	}
	$('#detailBtn').click(function(){
		if($('#detailedLoanResult').css('display') == "block"){
			$('#detailedLoanResult').hide();
		}else{
			$('#detailedLoanResult').show();
		}
	});
	
	//영업점연결 버튼 클릭 시
	$('#connectAgency').click(function(){
		(async () => {
		const inputOptions = new Promise((resolve) => {
			type:"info",
			  setTimeout(() => {
			    resolve({
			      '1': '1. 서류 직접 제출',
			      '2': '2. 서류 온라인 제출 ',
			    })
			  }, 1000)
			})

			const { value: color } = await Swal.fire({
			  type:'info',
			  title:"영업점 연결 ",
			  input: 'radio',
			  inputOptions: inputOptions,
			  inputValidator: (value) => {
			    if (!value) {
			      return 'You need to choose something!'
			    }
			  }
			})
			if (color) {
				if(color == '1'){
					Swal.fire({
								type:'info',
								html: '<br><br>서류를 지참하시고 해당 영업점을 방문해주세요.<br> 감사합니다<br><br>'
						+'<div style=\'font-size:0.8em; \'>1. 재직증명서<br>2. 원천징수1년<br>3. 갑종근로소득영수증(재직 1년미만)<br>(사업자의 경우 사업자등록증 사본,소득금액증명원)</div>'
						+'<div style=\'text-align:right; \'></div>' 
						})
				}else{
					$.ajax({
						url:"/addApply",
						method:"put",
						contentType: "application/x-www-form-urlencoded; charset=UTF-8",
						success: function(data){
							console.log(data);
						},
						error: function(e){
							console.log(e);
						}
					});
					Swal.fire({
	            		  type: 'success',
	            		  title:"영업점에 성공적으로 연결되었습니다.",
	            		  showConfirmButton: false,
	            		  timer: 1500
	            	});
					setTimeout(function() {
						location.href='/mypage';
						}, 100);
					}
				}
			})()
	});
	
	//대출진단 : 신규대출계산하기버튼 클릭시
	$('#newLoanBtn').click(function(){
		if($('#accountMoney').val() == "" ||
		   $('.accountPeriod').val() == "" || $('.accountRate').val() == ""){
			Swal.fire({
				  type: 'error',
				  text: '정보를 정확히 입력해주세요.',
				})
		}else{
			var accountMoney = Number($('#accountMoney').val());
			var accountPeriod = Number($('#accountPeriodd').val());
			var accountRate = Number($('#accountRatee').val());
			var result = 0;
			if($('#guaranteeType').val() == '6'){
				result = (accountMoney/accountPeriod) *(1.4+(accountRate/100));
			}else if($('#guaranteeType').val() == '3'){
				result = (accountMoney/8) *(1+(accountRate/100));
			}else if($('#guaranteeType').val() == '4'){
				result = (accountMoney/4) *(1+(accountRate/100));
			}else if($('#guaranteeType').val() == '5'){
				result = (accountMoney*(accountRate/100));
			}else{
				result = (accountMoney/10) *(1+(accountRate/100));
			}
			$('.col-12-small').show();
			$('#addNewLoanResult').val(addComma(result)+"원");
			$('#totalYearPayment').val(addComma(Number(cfNumeric($('#totalYearPayment').val()))+result)+"원");
			
			var result = Math.floor(Number(cfNumeric($('#totalYearPayment').val())) / Number(cfNumeric($('#customerIncome').val())) * 100,3);
			$('#dsrResult').val(result + "%");
		}
	});
	  
	//대출진단
	$('#incomeType3').change(function(){
		var value = $(this).val();
		if(value=='전문직'){
			$('#incomeType4').show();
		}else{
			$('#incomeType4').hide();
		}
	});
	$('#incomeType2').change(function(){
		var value= $(this).val();
		if(value=='지역보험가입자'){
			$('.annualIncome').hide();
			$('#insurance').show();
			$('.desc').hide();
			$('#descInsurance').show();
		}else if(value == '신용카드사용자'){
			$('.annualIncome').hide();
			$('#creditCard').show();
			$('.desc').hide();
			$('#descCreditCard').show();
		}
	});
	
	$('#incomeType1').change(function(){
		var value= $(this).val();
		if(value == '소득없음'){
			$('#incomeType2').show();
			$('#incomeType3').hide();
			$('#incomeType4').hide();
			$('.desc').hide();
		}else if(value =='근로소득자'){
			$('#incomeType2').hide();
			$('#incomeType3').show();
			$('.annualIncome').hide();
			$('#defaultIncome').show();
			$('.desc').hide();
			$('#descEmployee').show();
		}else if(value == '연금수급자'){
			$('#incomeType2').hide();
			$('#incomeType3').hide();
			$('#incomeType4').hide();
			$('.annualIncome').hide();
			$('#defaultIncome').show();
			$('.desc').hide();
			$('#descPension').show();
		}else if(value=='사업소득'){
			$('#incomeType2').hide();
			$('#incomeType3').hide();
			$('#incomeType4').hide();
			$('.annualIncome').hide();
			$('#defaultIncome').show();
			$('.desc').hide();
			$('#descBusiness').show();
		}
	});
	$('.myOtherBankLoanDelBtn').click(function(){
		var id = $(this).attr("id");
		Swal.fire({
			  title: '타행정보를 삭제하시겠습니까?',
			  text: "You won't be able to revert this!",
			  type: 'warning',
			  showCancelButton: true,
			  confirmButtonColor: '#3085d6',
			  cancelButtonColor: '#d33',
			  confirmButtonText: 'Yes, delete it!'
			}).then((result) => {
			  if (result.value) {
			    Swal.fire(
			      'Deleted!',
			      'Your info has been deleted.',
			      'success'
			    )
			    setTimeout(function() {
					location.href='/deleteMypage?no='+id;
					}, 1500);
			  }
			})
	});
	$('#delOtherBank').click(function(){
		var addLoanInfo = document.getElementById('addLoanInfo');
		if (addLoanInfo.rows.length < 1) return;
		addLoanInfo.deleteRow( addLoanInfo.rows.length-1 ); // 하단부터 삭제
    });
	
	$('#checkLoanMyselfBtn').click(function(){
		(async () => {
			/* inputOptions can be an object or Promise */
			const inputOptions = new Promise((resolve) => {
			  setTimeout(() => {
			    resolve({
			      '1': '대출연장',
			      '2': '대출신규',
			    })
			  }, 1000)
			})
	
		const { value: color } = await Swal.fire({
			  title: '연장 or 신규 대출',
			  input: 'radio',
			  inputOptions: inputOptions,
			  inputValidator: (value) => {
			    if (!value) {
			      return 'You need to choose something!'
			    }
			  }
		});
			if (color) {
				location.href='/myStatus/'+color;
			}
			})()
	});
	
});

function addComma(num) {
	  var regexp = /\B(?=(\d{3})+(?!\d))/g;
	  return num.toString().replace(regexp, ',');
}
function cfNumeric(sOrg) {
    var nm;
    sOrg = sOrg.replace(/,/g,""); 
    nm = parseFloat(sOrg).toString();
    return (isNaN(nm)?0:nm);
}
// 확장자 체크
function checkExtension(){
	var file1 = $("#originFileName1").val();
	var file2 = $("#originFileName2").val();
	var file3 = $("#originFileName3").val();

	if(file1 == "" || file2 =="" || file3 == ""){
		Swal.fire({
			type:"error",
			html:"서류를 모두 제출해주세요."
		})
		return false;
	}
	for(var inx = 1 ;inx <=3; inx++){
		var file = $("#originFileName"+inx).val();
		var last = file.lastIndexOf(".");
		var ext = file.substring(last+1,file.length).toLowerCase();
		
		if(ext != "pdf" && ext != "hwp" && ext != "doc" && ext != "jpg"  && ext != "png" && ext != "gif"){
			Swal.fire({
				type:"error",
				html:"서류의 확장자를 확인해주세요."
			});
			return false;
		}
	}
	return true;
}


function checkLoanForm(){
	var incomeType1 = $('#incomeType1').val();
	var incomeType2 = $('#incomeType2').val();
	var incomeType3 = $('#incomeType3').val();
	var incomeType4 = $('#incomeType4').val();
	var incomeMoney = $('#imcomeMoney').val();
	
	if(incomeType1 == 0){
		alert("소득정보를 정확하게 입력해주세요.");
		return false;
	}else if(incomeType1 == 1 || incomeType1 == 2){
		if(incomeType2 == 0){
			alert("소득정보를 정확하게 입력해주세요.");
			return false;
		}
	}else if(incomeMoney == ""){
		alert("소득정보를 정확하게 입력해주세요.");
		$('#incomeMoney').focus();
		return false;
	}
	return true;
}

(function($) {

	var	$window = $(window),
		$body = $('body');

	// Breakpoints.
		breakpoints({
			xlarge:  [ '1281px',  '1680px' ],
			large:   [ '981px',   '1280px' ],
			medium:  [ '737px',   '980px'  ],
			small:   [ null,      '736px'  ]
		});

	// Play initial animations on page load.
		$window.on('load', function() {
			window.setTimeout(function() {
				$body.removeClass('is-preload');
			}, 100);
		});

	// Dropdowns.
		$('#nav > ul').dropotron({
			mode: 'fade',
			noOpenerFade: true,
			alignment: 'center'
		});

	// Nav.
		// Title Bar.
			$(
				'<div id="titleBar">' +
					'<a href="#navPanel" class="toggle"></a>' +
				'</div>'
			)
				.appendTo($body);

		// Panel.
			$(
				'<div id="navPanel">' +
					'<nav>' +
						$('#nav').navList() +
					'</nav>' +
				'</div>'
			)
				.appendTo($body)
				.panel({
					delay: 500,
					hideOnClick: true,
					hideOnSwipe: true,
					resetScroll: true,
					resetForms: true,
					side: 'left',
					target: $body,
					visibleClass: 'navPanel-visible'
				});

})(jQuery);