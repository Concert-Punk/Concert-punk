

var vidWidth = 400;
var vidHeight = 300;
var vidResults = 1;





//The q parameter specifies the query term to search for.
// run get request on api
function youtubeSearch(q) {
    $.get(
        "https://www.googleapis.com/youtube/v3/search", {
            part: "snippet, id",
            q: q,
            type: "video",
            maxResults: vidResults,
            key: "AIzaSyBylEuSlqvJ3UM2PPqrRkW2vNPZQo1ccuo",
        },
        function(data) {
            var output;
            $.each(data.items, function(i, item) {
                console.log(item);
                var videoId = item.id.videoId;
                output = '<li><iframe height="' + vidHeight + '" width="' + vidWidth + '" src=\"https://www.youtube.com/embed/' + videoId + '\"></li>';
                $("#youTube").html(output);

            });
        }
    );
}

$(document).on("click", "#add-artist", function(event) {
    event.preventDefault();
    var q = $("#search-input").val().trim();
    youtubeSearch(q);


});

$(document).on("click", ".search-keyword", function() {
    var q = $(this).text();
    youtubeSearch(q);
});
