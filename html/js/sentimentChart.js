$( document ).ready(function()
{
	// retrive data from server
	$.getJSON('http://localhost/getGlobalData.php', function (json_data)
	{
		//json_data = '{"data":['+
		//				'{"data_time": 1147631200000,"total": 3, "positive": 5, "negative": 2, "neutral": 10}, {"data_time": 1148641200000, "total": 4, "positive": 6, "negative": 2, "neutral": 10},{"data_time": 1149651200000, "total": 3, "positive": 6, "negative": 3, "neutral": 10},{ "data_time": 1167661200000, "total": -3, "positive": 4, "negative": 7, "neutral": 10}]}';
		//json_data = '{ "data":[[1147651200000, 3, 5, 2],[1247651300000, 4, 6, 2],[1347651400000, 5, 7, 2],[147651500000, 6, 8, 3]]}';
		//console.log(json_data);
		positive = [];
		negative = [];
		neutral = [];
		total = [];
		data = json_data;
		for(i=0;i<data.length; i++)
		{
			//console.log("json data: "+data[i].data_time)
			//positive[i] = [parseInt(data[i].full_date_time),parseInt(data[i].positive_sentiment)];
			//negative[i] = [parseInt(data[i].full_date_time), parseInt(data[i].negative_sentiment)];
			//total[i] = [parseInt(data[i].full_date_time), parseInt(data[i].sentiment_value)];
			//neutral[i] = [parseInt(data[i].full_date_time), parseInt(data[i].neutral_sentiment)];
			positive[i] = [data[i].full_date_time,data[i].positive_sentiment];
			negative[i] = [data[i].full_date_time, data[i].negative_sentiment];
			total[i] = [data[i].full_date_time, data[i].sentiment_value];
			neutral[i] = [data[i].full_date_time, data[i].neutral_sentiment];
		}
		//console.log(positive);
		seriesOptions = [];
		// series 0 is the one showed in navigator
		seriesOptions[0] = {
			name: "Sentiment Value",
			//allowPointSelect: true,
			lineWidth: 2,
			color: Highcharts.getOptions().colors[1],
			data: total,
			zIndex: 2,
			cursor: 'pointer',
			enableMouseTracking: true,
			point:
			{
				events:
				{
					click: function()
					{
						updateSideChart(this.category);
					},
				},
			},
			marker:
			{
				enabled: true,
			},
		};
		seriesOptions[1] = {
			name: "Positive Sentiment",
			data: positive,
			zIndex: 1,
			lineWidth: 0.5,
			type: 'area',
			color: Highcharts.getOptions().colors[2],
			fillColor : {
				linearGradient : {
					x1: 0, 
					x2: 0,
					y1: 0,
					y2: 1,
				},
				stops : [
					[0, Highcharts.getOptions().colors[2]],
					[1, Highcharts.Color(Highcharts.getOptions().colors[2]).setOpacity(0).get('rgba')],
						],
			},
		};
		seriesOptions[2] = {
			name: "Negative Sentiment",
			data: negative,
			color: Highcharts.getOptions().colors[3], /* 0 = blu, 1 = grigio, 2 = verde, 3 = arancio, 4 = bluette, 5 = fuxia, 6 = giallo, 8 = rosso scuro */
			zIndex: 1,
			lineWidth: 0.5,
			type: 'area',
			fillColor : {
				linearGradient : {
					x1: 0, 
					x2: 0,
					y1: 0,
					y2: 1,
				},
				stops : [
					[0, Highcharts.getOptions().colors[3]],
					[1, Highcharts.Color(Highcharts.getOptions().colors[3]).setOpacity(0).get('rgba')],
						],
			},
		};
		
		$('#globalChart').highcharts('StockChart',
		{
			/* my option */
			chart : {
                //type: 'candlestick',
                zoomType: 'x'
            },
			title:{
				text: 'Global Sentiment Polarity'
			},
			scrollbar: 
			{
				enabled: false
			},
			rangeSelector:
			{
				allButtonEnabled: true,
				inputEnabled: true,
				selected: 5
			},
			tooltip:
			{
				crosshairs: [{dashStyle: 'dash', width: 1, color: 'black'},{dashStyle: 'dash', width: 1, color: 'black'}],
				pointFormat: '<span style="color:{series.color}">{series.name}</span>: <b>{point.y:.0f}</b><br/>',
			},
			xAxis:
			{
				gridLineWidth: 0.5
			},
			yAxis:
			{
				gridLineWidth: 0.5,
				labels: {
					formatter: function () {
						return (this.value);
					}
				},
				plotLines: [{
					value: 0,
					width: 1,
					color: 'black',
					dashStyle: 'dash',
				}],
			},
			
			plotOptions:
			{
				series:
				{
					enableMouseTracking: false,
				},
			},
			
			legend:
			{
				enable: true,
			},
			
			series: seriesOptions,
		});
		
		function updateSideChart(time)
		{
			updateMapChart(time);
			updatePieChart(time);
		}
		
		mapValue = [];
		mapData = Highcharts.geojson(Highcharts.maps['custom/world-highres']);
		
		//createPieChart(1,1,1,0); //on load, load emply chart
		//createMapChart(0); //on load, load emply chart
		updatePieChart(0);
		updateMapChart(0);
		
		function createPieChart(numPositive, numNegative, numNeutral, numTotal)
		{
			$('#comparisonChart').highcharts({
				chart : {
				//	borderWidth : 1
				},
				title: {
					text: 'Distribuition by Sentiment Polarity'
				},
				tooltip: {
					pointFormat: '{series.name}: <b>{point.y:.0f}</b>/'+numTotal+'</b>'
				},
				plotOptions: {
					pie: {
						//allowPointSelect: true,
						//cursor: 'pointer',
						dataLabels: {
							enabled: true,
							format: '<b>{point.name}: </b>{point.percentage:.2f} %',
							style: {
								color: (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black'
							}
						}
					}
				},
				series: [{
					type: 'pie',
					name: 'value',
					data: [{
								name: 'Positive Entities',
								color: Highcharts.getOptions().colors[2],
								y: numPositive
							},
							{
								name: 'Negative Entities',
								color: Highcharts.getOptions().colors[3],
								y: numNegative
							},
							{
								name: 'Neutral Entities',
								color: Highcharts.getOptions().colors[1],
								y: numNeutral
							},
					]
				}]
			});
		}
		
		function updatePieChart(time)
		{
			$('#comparisonChart').empty();
			selected = -1;
			numPositive = 0;
			numNegative = 0;
			numNeutral = 0;
			for(i=0;i<positive.length; i++)
			{
				supp = positive[i];
				this_time = supp [0];
				if(this_time == time)
				{
					selected = i;
					break;
				}
				
				supp  = positive[i];
				numPositive += supp[1];
				supp  = negative[i];
				numNegative += supp[1];
				supp  = neutral[i];
				numNeutral += supp[1];
			}
			if(selected < 0)
			{
				//supp  = positive[selected];
				//numPositive = supp[1];
				//supp  = negative[selected];
				//numNegative = supp[1];
				//supp  = neutral[selected];
				//numNeutral = supp[1];
				//createPieChart(1,1,1,0);
			}
			else
			{
				supp  = positive[selected];
				numPositive = supp[1];
				supp  = negative[selected];
				numNegative = supp[1];
				supp  = neutral[selected];
				numNeutral = supp[1];
			}
			createPieChart(numPositive, numNegative, numNeutral, (numPositive+numNegative+numNeutral));
		}
		
		function createMapChart(time)
		{
			$('#bubblemapChart').highcharts('Map', 
			{
				chart : {
					//borderWidth : 1
				},

				title: {
					text: 'Data source by Country'
				},

				legend: {
					enabled: false
				},

				mapNavigation: {
					enabled: true,
					buttonOptions: {
						verticalAlign: 'bottom'
					}
				},

				series : [{
					name: 'Countries',
					mapData: mapData,
					color: '#E0E0E0',
					enableMouseTracking: false
				},{
					type: 'mapbubble',
					mapData: mapData,
					name: 'Country: number',
					joinBy:  ['iso-a2', 'code'],
					data:  mapValue,
					minSize: '1%',
					maxSize: '15%',
					tooltip: {
						pointFormat: '{point.code}: <b>{point.z:.0f}</b>'
					}
				}],
			});
		}
		
		function updateMapChart(time)
		{
			//get new data from server
			//$.getJSON('http://www.highcharts.com/samples/data/jsonp.php?filename=world-population.json&callback=?', function (data) {
			$.getJSON('getGeoData.php?time='+time, function (data) {
				//console.log(data);
				mapValue = data;
				console.log(data);
				$('#bubblemapChart').empty();
				createMapChart(time);
			});
		};
    }); //json callback
});
