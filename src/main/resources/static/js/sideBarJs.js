$(document).ready(function () {
    $("#sidebar").mCustomScrollbar({
        theme: "minimal"
    });

    $('#dismiss, .overlay').on('click', function () {
        $('#sidebar').removeClass('active');
        $('.overlay').removeClass('active');
    });

    $('#sidebarCollapse').on('click', function () {
        $('#sidebar').addClass('active');
        $('.overlay').addClass('active');
        $('.collapse.in').toggleClass('in');
        $('a[aria-expanded=true]').attr('aria-expanded', 'false');
    });
});

//Seach functionailty was in sideBar, No need since it will be on its own page.

//
//     var vidWidth = 400;
//     var vidHeight = 300;
//     var vidResults = 10;
//
//
//
//
//
//     //The q parameter specifies the query term to search for.
//     // run get request on api
//     function youtubeSearch(q) {
//     $.get(
//         "https://www.googleapis.com/youtube/v3/search", {
//             part: "snippet, id",
//             q: q,
//             type: "video",
//             maxResults: vidResults,
//             key: 'AIzaSyBylEuSlqvJ3UM2PPqrRkW2vNPZQo1ccuo',
//
//         },
//         function(youTubeBasicData) {
//             var output;
//             $.each(youTubeBasicData.items, function(i, item) {
//
//                 console.log(item);
//                 let videoId = item.id.videoId;
//                 document.getElementById('youTube').innerHTML  += '<li><iframe height="' + vidHeight + '" width="' + vidWidth + '" src=\"https://www.youtube.com/embed/' + videoId + '\"></li>';
//                 $("#youTube").html(output);
//                 //  console.log(item.snippet.description);
//
//
//
//                 let durationUrl = 'https://www.googleapis.com/youtube/v3/videos?part=contentDetails&id=' + videoId + '&key=AIzaSyBylEuSlqvJ3UM2PPqrRkW2vNPZQo1ccuo'
//                 let viewCountUrl = 'https://www.googleapis.com/youtube/v3/videos?part=statistics&id=' + videoId + '&key=AIzaSyBylEuSlqvJ3UM2PPqrRkW2vNPZQo1ccuo'
//                 axios.get(durationUrl).then(resp => {
//                     //1.Duration and plays dont always match appropriate track, will need to match everything by the videoID
//                     //2.I need to iterate through them not happening so far
//                     axios.get(viewCountUrl).then(response => {
//                         let dynamicBoii=    `   <tr>
//                         <th scope="row"><a href="">+</a></th>
//                         <td id="description">${item.snippet.title}</td>
//                         <td id="artist">${item.snippet.channelTitle}</td>
//                         <td id="duration">${resp.data.items[0].contentDetails.duration}</td>
//                         <td id="viewCount">${ response.data.items[0].statistics.viewCount}</td>
//                     </tr>`;
//                         document.getElementById('youtubeData').innerHTML += dynamicBoii;
//                         console.log(response.data)
//                         console.log(response.data.items[0].statistics.viewCount)
//
//                     })
//
//
//                     console.log(resp.data);
//                 });
//
//
//             });
//
//         }
//
//     );
// }
//
//     $(document).on("click", "#add-artist", function(event) {
//     event.preventDefault();
//     var q = $("#search-input").val().trim();
//     youtubeSearch(q);
//
//
// });
//
//     $(document).on("click", ".search-keyword", function() {
//     var q = $(this).text();
//     youtubeSearch(q);
// });
//
//     $(document).ready(function() {
//
//     $('#tableLinks').click(function() {
//         var href = $(this).find("a").attr("href");
//         if(href) {
//             window.location = href;
//         }
//     });
//
// });