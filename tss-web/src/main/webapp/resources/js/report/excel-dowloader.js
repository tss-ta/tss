$('#download').on('click', function() {
    var file = {
        worksheets: [[]], // worksheets has one empty worksheet (array)
        creator: 'Taxi Service System', created: new Date(),
        lastModifiedBy: 'Taxi Service System', modified: new Date(),
        activeWorksheet: 0,
        name: "test-name"
    }, w = file.worksheets[0]; // cache current worksheet
    w.name = 'name123';
    w.data = [['ab', 'cd'], ['ef', 'gk']];

    $('#download').attr('href', xlsx(file).href());
//    window.location = xlsx(file).href();
});