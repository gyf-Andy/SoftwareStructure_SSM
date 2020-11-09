<%--
  Created by IntelliJ IDEA.
  User: 22413
  Date: 2020/10/19
  Time: 19:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>使用ajax实现异步信息校验</title>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.3.1.js"></script>
    <script src="${pageContext.request.contextPath}/js/echarts.min.js" type="text/javascript"></script>
    <script type="text/javascript">
        function fun_isHadTheUser(){
            var name=document.getElementById("name").value;
            //异步请求jq中的ajax
            $.ajax({
                type:"get",
                url:"${pageContext.request.contextPath}/admin/isHadTheUser.action",
                data:{
                    "name":name
                },
                success:function(msg){
                    //alert(msg.code+" "+msg.msg+" "+msg.data);
                    document.getElementById("isHadUser").innerText=msg.msg;
                },
                error:function(){
                    alert("请求失败");
                }

            })
        }

        //查询所有的分类，显示到select里面
         function fun_setClassify(){
            $.ajax({
                type: "get",
                url: "${pageContext.request.contextPath}/admin/searchAllClassify.action",
                success:function(msg){
                    var classifyArray=msg.data;
                    var str="";
                    for (var i=0;i<classifyArray.length;i++){
                        str+="<option value="+classifyArray[i].id+">"+classifyArray[i].classifyName+"</option>";
                    }
                    document.getElementById("classifyName").innerHTML=str;
                }
            })
        }

        //根据食物分类的的id查询食物名
        function fun_searchFoodNameById(){
            var classifyId=document.getElementById("classifyName").value;
            $.ajax({
                type:"get",
                url:"${pageContext.request.contextPath}/admin/find.action",
                data:{
                    "classifyId":classifyId
                },
                success:function(msg){
                    var foodNameArray=msg.data;
                    var str="";
                    for (var i=0;i<foodNameArray.length;i++){
                        str+="<option value="+foodNameArray[i].classifyId+">"+foodNameArray[i].foodName+"</option>";
                    }
                    document.getElementById("foodName").innerHTML=str;
                },
                error:function(){
                    alert("请求失败");
                }
            })
        }

        //文件的上传
        function fun_fileUpload(){
            var location=document.getElementById("location");
            //alert(location.value);
            $.ajax({
                type:"get",
            })
        }

        //文件的下载
        function fun_filedownload(){
            $.ajax({
                type:"get",
                url:"${pageContext.request.contextPath}/admin/getCommodityAll.action",
                success:function(msg){
                    //alert(msg.code+" "+msg.msg+" "+msg.data);
                    document.getElementById("downloadover").innerText=msg.msg;
                },
                error:function(){
                    document.getElementById("downloadover").innerText="发生未知错误下载失败";
                }

            })
        }

    </script>
</head>
<body onload="fun_setClassify()">

        测试<br>
        <form action="${pageContext.request.contextPath}/admin/searchAdminInfo.action" method="get">
            <input type="submit" name="查询"/>
        </form>

        ${admin}

        <form>
            用户名：<input type="text" name="name" id="name" onblur="fun_isHadTheUser()">
            <div id="isHadUser" style="color: #FF0000"></div>
            <br>
            密  码：<input type="password" name="password"><br>
            <!--<input type="button" value="提交">-->
            <br/>
            <br/>
            <br/>

            食品分类：
            <select name="classify" id="classifyName" onchange="fun_searchFoodNameById()">

            </select>
            食品名称：
            <select name="classify" id="foodName">

            </select>

        </form>

        <br/>

        <!-- 为ECharts准备一个具备大小（宽高）的Dom -->
        <div id="main" style="width: 600px;height:400px;"></div>
        <script type="text/javascript">
            // 基于准备好的dom，初始化echarts实例
            var myChart = echarts.init(document.getElementById('main'));

            // 指定图表的配置项和数据
            var option = {
                title: {
                    text: 'ECharts 入门示例'
                },
                tooltip: {},
                legend: {
                    data:['销量']
                },
                xAxis: {
                    data: ["衬衫","羊毛衫","雪纺衫","裤子","高跟鞋","袜子"]
                },
                yAxis: {},
                series: [{
                    name: '销量',
                    type: 'bar',
                    data: [5, 20, 36, 10, 10, 20]
                }]
            };

            // 使用刚指定的配置项和数据显示图表。
            myChart.setOption(option);
        </script>

        <h3>使用ajax动态访问数据库数据</h3>
        <div id="getCommodityData" style="height: 400px;width: 600px "></div>

        <script type="text/javascript">

            $.ajax({
                type:"get",
                url:"${pageContext.request.contextPath}/admin/getCommodityData.action",
                success:function(msg){
                    var x=new Array();
                    var y=new Array();


                    for(var i=0;i<msg.data.length;i++){
                        x[i]=msg.data[i].commodityName;
                        y[i]=msg.data[i].commodityNumber;
                    }

                    // 基于准备好的dom，初始化echarts实例
                    var myChart = echarts.init(document.getElementById('getCommodityData'));

                    // 指定图表的配置项和数据
                    option = {
                        color: ['#3398DB'],
                        tooltip: {
                            trigger: 'axis',
                            axisPointer: {            // 坐标轴指示器，坐标轴触发有效
                                type: 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
                            }
                        },
                        grid: {
                            left: '3%',
                            right: '4%',
                            bottom: '3%',
                            containLabel: true
                        },
                        xAxis: [
                            {
                                type: 'category',
                                data: x,
                                axisTick: {
                                    alignWithLabel: true
                                }
                            }
                        ],
                        yAxis: [
                            {
                                type: 'value'
                            }
                        ],
                        series: [
                            {
                                name: '直接访问',
                                type: 'bar',
                                barWidth: '60%',
                                data: y
                            }
                        ]
                    };

                    // 使用刚指定的配置项和数据显示图表。
                    myChart.setOption(option);
                },
                error:function(){
                    alert("请求失败");
                }
            })

        </script>

        <!--文件上传与下载-->

        文件上传（表格文件）
        <br/>
        请输入文件路径：(因为浏览器的假路径问题 C:\fakepath\... 问题，现在不得不采用这种方法)
        <br/>
        <textarea id="location"></textarea>
        <!--<input id="fileUpload" type="file" name="file"/>-->
        <input type="button"  id="caupload" value="上传文件" onclick="fun_fileUpload()" />(此功能未实现)

        <br/>
        <br/>

        文件下载（表格文件）

        <br/>
        <!--<input id="file" type="file" name="file"/> 如果要改为选取文件夹，那么需要添加webkitdirectory multiple。记得要把提示文字改为文件夹。-->
        <input type="button" id="download" value="点击下载commodity表中的数据" onclick="fun_filedownload()" />位置（E:\POI技术输入输出表格实验文件夹）
        <br/>
        <span id="downloadover" style="color: #FF0000;"></span>


        <br>
        <br>
        <a href="${pageContext.request.contextPath}/goods/toAddGoodsPage.action">去上传图片的页面</a>


</body>
</html>
