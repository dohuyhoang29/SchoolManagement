$(document).ready(function () {
  var options = {
    series: [14, 23, 21, 17, 15, 10, 12, 17, 21],
    chart: {
      type: 'polarArea',
    },
    stroke: {
      colors: ['#fff']
    },
    fill: {
      opacity: 0.8
    },
    responsive: [{
      breakpoint: 480,
      options: {
        chart: {
          width: 200
        },
        legend: {
          position: 'bottom'
        }
      }
    }]
  };

  var chart = new ApexCharts(document.querySelector("#chart-last-year"), options);
  chart.render();
})

$(document).ready(function() {
  const listYear = [];

  for (let i =0; i < 10; i++) {
    listYear[i] = new Date().getFullYear() - i;
  }

  const options = {
    series: [{
      name: 'series1',
      data: [31, 40, 28, 51, 42, 109, 100, 13, 34, 56]
    }, {
      name: 'series2',
      data: [11, 32, 45, 32, 34, 52, 41, 12, 56, 12]
    }],
    chart: {
      height: 430,
      width: 750,
      type: 'area'
    },
    dataLabels: {
      enabled: false
    },
    stroke: {
      curve: 'smooth'
    },
    xaxis: {
      type: 'year',
      categories: [listYear[0], listYear[1], listYear[2], listYear[3],
        listYear[4], listYear[5], listYear[6], listYear[7], listYear[8],
        listYear[9]]
    },
    tooltip: {
      x: {
        format: 'dd/MM/yy HH:mm'
      },
    },
  };

  const chart = new ApexCharts(document.querySelector("#chart-last-10-year"),
      options);
  chart.render();
});