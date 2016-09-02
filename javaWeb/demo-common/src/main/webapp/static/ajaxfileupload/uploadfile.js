function uploadfile(uploadElememt,uploadData, valueElement, imageElement) {
	try {
		$.ajaxFileUpload({
			url : _ctxPath+'/picture/add.do',
			secureuri : false,
			data:uploadData,
			fileElement : uploadElememt,
			dataType : 'json',
			success : function(data) {
				if(data["checkFlag"]=="yes"){
					valueElement.val(data["relativeUrl"]);
					imageElement.attr('src', data["imageUrl"]);
				}else if(data["checkFlag"]=="errorExt"){
					alert("图片格式不正确，请选择jpg、gif、bmp格式的图片！");
				}else if(data["checkFlag"]=="errorPoint"){
					alert("图片文件名中有特殊字符，请去掉特殊字符！");
				}else if(data["checkFlag"]=="errorSize"){
					alert("图片太大，请选择30M以内图片！");
				}
			},
			error : function(data, status, e) {
				alert("图片上传错误！");
			}
		});
	} catch (e) {
		alert("图片上传错误！");
	}
}