$(document).ready(function(){
    $("#searchBtn").click(function(){
        let keyword = $("#keyword").val().trim();
        if(keyword.length === 0){
            alert("请输入搜索关键字！");
            return;
        }
        $.ajax({
            url: "/vinyl/searchAjax",
            method: "GET",
            data: { keyword: keyword },
            success: function(data){
                // data是一个Vinyl列表
                $("#resultBody").empty();
                if(data.length === 0){
                    $("#resultTable").hide();
                    alert("未找到结果");
                } else {
                    $("#resultTable").show();
                    data.forEach(function(v){
                        let row = `<tr>
                            <td>${v.artist}</td>
                            <td>${v.title}</td>
                            <td><a href="/vinyl/detail/${v.id}">查看详情</a></td>
                        </tr>`;
                        $("#resultBody").append(row);
                    });
                }
            },
            error: function(){
                alert("搜索失败，请稍后重试");
            }
        });
    });
});
