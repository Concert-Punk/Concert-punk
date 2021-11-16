

    let vidWidth = 400;
    let vidHeight = 300;
    let vidResults = 10;


    //The q parameter specifies the query term to search for.
    // run get request on api
    function youtubeSearch(q) {
    $.get(
        "https://www.googleapis.com/youtube/v3/search", {
            part: "snippet, id",
            q: q,
            type: "video",
            maxResults: vidResults,
            key: 'AIzaSyC0wIOwWh6wV4cRxWb1F8cApvRWkuqxZbQ',

        },
        function (youTubeBasicData) {
            let output;
            $.each(youTubeBasicData.items, function (i, item) {

                // console.log(item);
                let videoId = item.id.videoId;
                // document.getElementById('youTube').innerHTML  += '<li><iframe height="' + vidHeight + '" width="' + vidWidth + '" src=\"https://www.youtube.com/embed/' + videoId + '\"></li>';
                $("#youTube").html(output);
                //  console.log(item.snippet.description);


                let durationUrl = 'https://www.googleapis.com/youtube/v3/videos?part=contentDetails&id=' + videoId + '&key=AIzaSyC0wIOwWh6wV4cRxWb1F8cApvRWkuqxZbQ'
                let viewCountUrl = 'https://www.googleapis.com/youtube/v3/videos?part=statistics&id=' + videoId + '&key=AIzaSyC0wIOwWh6wV4cRxWb1F8cApvRWkuqxZbQ'
                axios.get(durationUrl).then(resp => {
                    let videoDuration = resp.data.items[0].contentDetails.duration

                    let durationConverter =  moment.duration(videoDuration).asMilliseconds()

                    let formattedTime = moment.utc(durationConverter).format('HH:mm:ss')

                    // console.log(moment.utc(ms + 3600000).format('HH:mm:ss'));

                    //1.Duration and plays dont always match appropriate track, will need to match everything by the videoID
                    //2.I need to iterate through them not happening so far
                    axios.get(viewCountUrl).then(response => {

                        //Grabbing onclick action on the table rows just need to plug in function/variable to pass videourl to play it.
                        let dynamicBoii = `   <tr  >
                        <th scope="row">
                        <span class="material-icons playerButton videoToggler" data-id="${item.id.videoId}"  onclick="playVideo()" id="playBtn"> play_circle </span>
                      
 </th>
                        <td id="description">${item.snippet.title}</td>
                        <td id="artist">${item.snippet.channelTitle}</td>
                        <td id="duration">${formattedTime}</td>
                        <td id="viewCount">${response.data.items[0].statistics.viewCount}</td>
                    </tr>`;
                        document.getElementById('youtubeData').innerHTML += dynamicBoii;
                        //
                        // console.log(response.data)
                        // console.log(response.data.items[0].statistics.viewCount)

                    })
                    // console.log(resp.data);
                });


            });

        }
    );
}


    $(document).on("click", "#add-artist", function (event) {
    event.preventDefault();
    let q = $("#search-input").val().trim();
    youtubeSearch(q);


});

    $(document).on("click", ".search-keyword", function () {
    let q = $(this).text();
    youtubeSearch(q);
});

    //Sams Script
    let player = null;
    let tag = document.createElement('script');

    tag.src = "https://www.youtube.com/iframe_api";
    let firstScriptTag = document.getElementsByTagName('script')[0];
    firstScriptTag.parentNode.insertBefore(tag, firstScriptTag);

    function onYouTubeIframeAPIReady() {
    $(document).on("click", 'span.videoToggler', function () {
        $(this).html("play_pause")
        let youTubeId = $(this).attr('data-id');

        document.querySelector("#playerWrapper").innerHTML = `<div id="player"></div>`

$('')
        player = new YT.Player('player', {
            height: '390',
            width: '800',
            videoId: youTubeId,
            playerVars: {
                'playsinline': 1
            },
            events: {
                'onReady': onPlayerReady,
            }
        });

    })
}


    function onPlayerReady(event) {
    // console.log(event.target)

    // event.target.playVideo();
    // event.target.setVolume(100);
}


