import {Component} from "react";
import dataType from 'prop-types';

class Props extends Component {

    /*
    constructor(props) {
        console.log("props :" + JSON.stringify(props));
        super(props);
    }
     */

    render() {
        /*
        let {
            String, Number, Boolean, Array, ObjectJson, Function
        } = this.props;
         */
        return (
            /*
            <div style={{padding: "0px"}}>
                <p>String: {String}</p>
                <p>Number: {Number}</p>
                <span>Boolean: {Boolean.toString(Boolean)}</span>
                <p>Object Json: {JSON.stringify(ObjectJson)}</p>
                <p>Function: {Function}</p>
            </div>
             */
            <div style={{padding: "0px"}}>
                {this.props.children}
            </div>
        )
    }
}

// Props 기본값 설정
Props.defaultProps = {
    String: "리액트",
    Number: 200
}

// Props 속성 설정
Props.propTypes = {
    String: dataType.string,
    Number: dataType.number,
    Boolean: dataType.bool,
    Array: dataType.array,
    ObjectJson: dataType.shape({
        name: dataType.string.isRequired,
        age: dataType.number.isRequired,
        gender: dataType.bool.isRequired
    }),
    Function: dataType.func,
}

export default Props;