import {Component} from 'react';

class Map extends Component {
    constructor(props) {
        super(props);
        this.state = {};
    }

    componentDidMount() {
        var arr = [3, 2, 1, 4];
        let newArr = arr.map(x => x);
        console.log("1. newArr : " + newArr);

        let multipleArr = arr.map(x => x * 2);
        console.log("2. multipleArr : " + multipleArr);

        var objArr = [
            {name : "bum", year: 33},
            {name : "ho", year: 32}
        ]
        let newObjArr = objArr.map((obj, index) => {
            console.log("  > " + index + ". obj : " + JSON.stringify(obj));
            var o = {};
            o[obj.name] = obj.year;
            return o;
        });
        console.log("3. objArr : " + JSON.stringify(newObjArr));
    }
    render() {
        return (
            <h2>[THIS IS Map]</h2>
        )
    }
}

export default Map;