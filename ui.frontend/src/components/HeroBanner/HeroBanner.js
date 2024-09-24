import React, {Component} from 'react';
import {MapTo} from '@adobe/aem-react-editable-components';

require('./HeroBanner.css');

export const HeroBannerEditConfig = {
    emptyLabel: 'Hero Banner',

    //if isEmpty returns true, the place holder in authoring will be rendered
    isEmpty: function(props) {
        return !props || !props.src || props.src.trim().length < 1;
    }
};

export default class HeroBanner extends Component {

    get content() {
    const backgroundImageUrl = this.props.src;

    const divStyle = {
        backgroundImage: `url(${backgroundImageUrl})`,
    };

            return <div class={this.props.classList} style={divStyle}>
                   </div>;
        }

    render() {
        if (HeroBannerEditConfig.isEmpty(this.props)) {
            return null;
        }

        return (
            <div className="HeroBanner">{this.content}</div>
        );
    }
}

MapTo('wknd-spa-react/components/herobanner')(HeroBanner, HeroBannerEditConfig);