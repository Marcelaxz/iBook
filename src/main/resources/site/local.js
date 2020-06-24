var margin = {top: 10, right: 30, bottom: 30, left: 60},
    width = 1060 - margin.left - margin.right,
    height = 400 - margin.top - margin.bottom;

var svg = d3.select("#my_dataviz")
  .append("svg")
  .attr("width", width + margin.left + margin.right)
  .attr("height", height + margin.top + margin.bottom)
  .append("g")
  .attr("transform",
     "translate(" + margin.left + "," + margin.top + ")");


d3.json("/api/Trends",

    function(data){

        document.getElementById("term").innerHTML = data.term;

        function Enviar(){
            alert('Pontuação: );
        }


        var parseDate = d3.timeParse("%Y-%m-%d");

        data = data.results.map(function(d) {
            d.date = parseDate(d.date);
            return d;
        });


        data = data.filter(
            function(d) {
                return d.value != null;
            }
        );

        var x = d3.scaleTime()
            .domain(d3.extent(data, function(d) { return d.date; }))
            .range([ 0, width ]);
        svg.append("g")
            .attr("transform", "translate(0," + height + ")")
            .call(d3.axisBottom(x));


        var y = d3.scaleLinear()
            .domain( d3.extent(data, function(d) { return d.value; }))
            .range([ height, 0 ]);
        svg.append("g")
            .call(d3.axisLeft(y));


        svg.append("path")
            .datum(data)
            .attr("fill", "none")
            .attr("stroke", "#0B3D91")
            .attr("stroke-width", 2)
            .attr("d", d3.line()
                .x(function(d) { return x(d.date) })
                .y(function(d) { return y(d.value) })
            )

        svg
          .append("g")
          .selectAll("dot")
          .data(data)
          .enter()
          .append("circle")
          .attr("cx", function(d) { return x(d.date) } )
          .attr("cy", function(d) { return y(d.value) } )
          .attr("r", 2.5)
          .attr("fill", "#FC3D21")
    })

