<template>
    <div class="about">
        <h1>This is an about page</h1>

    </div>
</template>
<script>
export default {

    methods: {
        data: {
            searchQuery: '',
            columns: [{name: 'name', iskey: true}, {name: 'age'},{name: 'sex', dataSource:['Male', 'Female']}],
            peoples: []
        },
        ready: function () {
            this.getPeoples();
        },
        getPeoples: function () {
            var vm = this;
            vm.$http.get('http://localhost:8081/api/gitlab/star').then(
                function (data) {
                    var newdata = JSON.parse(data.body)
                    vm.$set('peoples', newdata.result)
                }).catch()
        }
    }
}

</script>
