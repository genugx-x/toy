import { Component } from 'react';

class R011_SpreadOperator extends Component {
    constructor(props) {
        super(props);
        this.state = {};
    }

    componentDidMount() {
        // javascript Array
        var arr1 = ['num1', 'num2'];
        var arr2 = ['num3', 'num4'];
        var sumArr = [arr1[0], arr1[1], arr2[0], arr2[1]];
        var sumArrWithConcat = [].concat(arr1, arr2);
        console.log('1. sumArr : ' + sumArr);
        console.log('2. sumArrWithConcat : ' + sumArrWithConcat);

        // ES6 Array
        let sumArrWithEs6 = [...arr1, ...arr2];
        console.log('3. sumArrWithEs6 : ' + sumArrWithEs6);
        const [sum1, sum2, ...remain] = sumArrWithEs6;
        console.log('4. sum1 : ', + sum1 + ', sum2 : ' + sum2 + ', remain : ' + remain);

        var Obj1 = { key1 : 'val1', key2 : 'val2' };
        var Obj2 = { key3 : 'val3', key4 : 'val4' };
        //ES6 Object
        var sumObj = {...Obj1, ...Obj2};
        console.log('sumObj : ' + sumObj);
        console.log('sumObj(JSON) : ' + JSON.stringify(sumObj));

        var {key2, key3, ...other} = sumObj;
        console.log (key2, key3, JSON.stringify(other));
    }

    render() {
        return (
            <h2>[SpreadOperator]</h2>
        )
    }
}

export default R011_SpreadOperator