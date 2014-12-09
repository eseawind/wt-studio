	function indexUpdate(){
			var orderValue = document.forms[0]['index.indexorder'].value;
			if(orderValue != null&& orderValue.replace(/(^\s*)|(\s*$)/g, "") != ""){
				var patt = /^[0-9]+$/;
				if(!patt.test(orderValue)){
					alert('排序号必须为数字');
					return false;
				}
			}
			return true;
		}