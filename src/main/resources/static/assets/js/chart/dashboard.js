$(document).ready(function () {
  console.log($('#new-student-last-year').val());
  console.log($('#new-student-this-year').val());
  console.log($('#graduate-student-last-year').val());
  console.log($('#absent-student-last-year').val());
  console.log($('#fail-student-last-year').val());
  var options = {
    series: [$('#new-student-last-year').val(), $('#new-student-this-year').val(), $('#graduate-student-last-year').val(), $('#absent-student-last-year').val(), $('#fail-student-last-year').val()],
    chart: {
      type: 'polarArea',
    },
    stroke: {
      colors: ['#fff']
    },
    labels: ['Total new student last year', 'Total new student this year', 'Total student graduate last year', 'Total student absent last year', 'Total student fail last year'],
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