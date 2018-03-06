$(function(){
    $(".control-nationality-suggest").each(function () {
        var input = $(this).find(".nationality-suggest-input");
        var list = $(this).find(".nationality-suggest-list");
        new nationality({ input: input, list: list });
    })
})