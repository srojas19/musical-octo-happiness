$(function () {

    Morris.Line({
        element: 'morris-area-chart',
        data: [{
                period: '2016-1-1',
                FecuenciaCardiaca: 300,
                NivelEstres: 2,
                PresionSanguinea: 64
            }, {
                period: '2016-5-1',
                FecuenciaCardiaca: 240,
                NivelEstres: 5,
                PresionSanguinea: 80
            }, {
                period: '2016-11-1',
                FecuenciaCardiaca: 203,
                NivelEstres: 1,
                PresionSanguinea: 86
            }, {
                period: '2017-1-1',
                FecuenciaCardiaca: 150,
                NivelEstres: 2,
                PresionSanguinea: 90
            }, {
                period: '2016-1-1',
                FecuenciaCardiaca: 162,
                NivelEstres: 5,
                PresionSanguinea: 92
            }, {
                period: '2017-2-1',
                FecuenciaCardiaca: 100,
                NivelEstres: 4,
                PresionSanguinea: 95
            }, {
                period: '2017-3-2',
                FecuenciaCardiaca: 210,
                NivelEstres: 3,
                PresionSanguinea: 120
            }, {
                period: '2017-3-6',
                FecuenciaCardiaca: 180,
                NivelEstres: 4,
                PresionSanguinea: 142
            }, {
                period: '2017-3-21',
                FecuenciaCardiaca: 140,
                NivelEstres: 2,
                PresionSanguinea: 60
            }, {
                period: '2017-4-11',
                FecuenciaCardiaca: 90,
                NivelEstres: 2,
                PresionSanguinea: 63
            }],
        xkey: 'period',
        ykeys: ['FecuenciaCardiaca', 'NivelEstres', 'PresionSanguinea'],
        labels: ['Fecuencia Cardiaca', 'Nivel estres', 'Presion sanguinea'],
        pointSize: 2,
        hideHover: 'auto',
        resize: true
    });

    Morris.Donut({
        element: 'morris-donut-chart',
        data: [{
                label: "Rojo",
                value: 1
            }, {
                label: "Amarillo",
                value: 5
            }, {
                label: "Verde",
                value: 20
            }],
        colors: [
            'rgba(255, 98, 51, 1)',
            'rgba(255, 249, 51, 1)',
             '#0BA462'
        ],
        resize: true
    });

    Morris.Bar({
        element: 'morris-bar-chart',
        data: [{
                y: '2006',
                a: 100,
                b: 90
            }, {
                y: '2007',
                a: 75,
                b: 65
            }, {
                y: '2008',
                a: 50,
                b: 40
            }, {
                y: '2009',
                a: 75,
                b: 65
            }, {
                y: '2010',
                a: 50,
                b: 40
            }, {
                y: '2011',
                a: 75,
                b: 65
            }, {
                y: '2012',
                a: 100,
                b: 90
            }],
        xkey: 'y',
        ykeys: ['a', 'b'],
        labels: ['Series A', 'Series B'],
        hideHover: 'auto',
        resize: true
    });

});
