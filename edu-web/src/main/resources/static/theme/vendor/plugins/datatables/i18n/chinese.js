(function(){
    var oLanguage={
    		"sProcessing":   "数据读取中...",
    		"sLengthMenu":   "_MENU_",
    		"sZeroRecords":  "没有查询到符合条件的数据",
    		"sInfo":"显示 _START_ 至 _END_ 条，共 _TOTAL_ 条",
    		"sInfoEmpty":"显示 0 至 0 条，共 0 条",
    		"sInfoFiltered": "(由 _MAX_ 项结果过滤)",
    		"sInfoPostFix":  "",
    		"sSearch":"搜索:",
    		"sUrl":"",
    		"sEmptyTable":"没有查询到符合条件的数据",
    		"sLoadingRecords": '数据读取中...',
    		//"sLoadingRecords": '<img src="path/to/ajax-loader.gif">',
    		"sInfoThousands":",",
    		"oPaginate": {
    			"sFirst":    "首页",
    			"sPrevious": "前页",
    			"sNext":     "后页",
    			"sLast":     "末页"
    		},
    		"oAria": {
    			"sSortAscending":  ": 以升序排列此列",
    			"sSortDescending": ": 以降序排列此列"
    		}
    	}
    $.fn.dataTable.defaults.language=oLanguage;
})();