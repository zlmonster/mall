$(function () {
    $("#jqGrid").jqGrid({
        url: 'sys/generator/list',
        datatype: "json",
        colModel: [			
			{ label: '表名', name: 'tableName', width: 100, key: true },
			{ label: 'Engine', name: 'engine', width: 70},
			{ label: '表备注', name: 'tableComment', width: 100 },
			{ label: '创建时间', name: 'createTime', width: 100 }
        ],
		viewrecords: true,
        height: 385,
        rowNum: 10,
		rowList : [10,30,50,100,200],
        rownumbers: true, 
        rownumWidth: 25, 
        autowidth:true,
        multiselect: true,
        pager: "#jqGridPager",
        jsonReader : {
            root: "page.list",
            page: "page.currPage",
            total: "page.totalPage",
            records: "page.totalCount"
        },
        prmNames : {
            page:"page", 
            rows:"limit", 
            order: "order"
        },
        gridComplete:function(){
        	//隐藏grid底部滚动条
        	$("#jqGrid").closest(".ui-jqgrid-bdiv").css({ "overflow-x" : "hidden" }); 
        }
    });
});

var vm = new Vue({
	el:'#rrapp',
	data:{
		q:{
			tableName: null
		},
        apiName : null,
        serviceName : null,
        businessName:null,
        isChange : false,
        prefix : null

	},
	methods: {
		query: function () {
			$("#jqGrid").jqGrid('setGridParam',{ 
                postData:{'tableName': vm.q.tableName},
                page:1 
            }).trigger("reloadGrid");
		},
		generator: function() {
            var apiName = vm.apiName;
            var serviceName = vm.serviceName;
            var businessName = vm.businessName;
            var prefix = vm.prefix
            console.log("apiName:" + apiName + ", serviceName:" + serviceName + ", businessName:" + businessName + ",prefix:" + prefix);
            if(apiName == null || serviceName == null){
                alert("请输入参数");
                return;
            }
			var tableNames = getSelectedRows();
			if(tableNames == null){
				return ;
			}
            location.href = "sys/generator/code?tables=" + tableNames.join() + "&apiName=" + apiName
                + "&serviceName=" + serviceName + "&businessName=" + businessName + "&prefix=" + prefix;
		}

	}
});

